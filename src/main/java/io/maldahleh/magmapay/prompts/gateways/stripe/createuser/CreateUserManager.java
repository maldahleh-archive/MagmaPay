package io.maldahleh.magmapay.prompts.gateways.stripe.createuser;

import com.stripe.exception.*;

import io.maldahleh.magmapay.MagmaPay;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CreateUserManager {
    private MagmaPay magmaPay;
    private HashMap<Player, CreateUserProgressObject> createUserMap;

    public CreateUserManager(MagmaPay magmaPay) {
        this.magmaPay = magmaPay;
        this.createUserMap = new HashMap<>();

        Bukkit.getServer().getPluginManager().registerEvents(new CreateUserListener(magmaPay, this), magmaPay);
    }

    void handleMessage(final Player p, final String message) {
        if (message.equalsIgnoreCase("cancel") || message.equalsIgnoreCase("quit")) {
            p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserQuitSuccessful());

            removePlayer(p);
            magmaPay.getCacheManager().getCustomerRetrievalHashMap().get(p).countDown();
            return;
        }

        final CreateUserProgressObject progressObject = getPlayerObject(p);
        CreateUserStep currentStep = progressObject.getUserStep();

        switch (currentStep) {
            case EMAIL:
                boolean isValidEmail = ValidationUtils.validateEmail(message);

                if (isValidEmail) {
                    progressObject.setEmail(message);
                    progressObject.setUserStep(CreateUserStep.PIN);

                    p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserPin());
                } else {
                    p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserEmailError());
                    removePlayer(p);

                    magmaPay.getCacheManager().getCustomerRetrievalHashMap().get(p).countDown();
                }

                break;
            case PIN:
                boolean isValidPin = ValidationUtils.validatePin(message);

                if (isValidPin) {
                    Bukkit.getScheduler().runTaskAsynchronously(magmaPay,
                            () -> progressObject.setPinHash(Encryption.securePass(message)));

                    if (magmaPay.getLocalConfig().isCollectBillingAddress()) {
                        progressObject.setUserStep(CreateUserStep.ADDRESS);
                        p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserAddress());
                    } else {
                        progressObject.setUserStep(CreateUserStep.CC_NAME);
                        p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserName());
                    }
                } else {
                    p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserPinError());
                    removePlayer(p);

                    magmaPay.getCacheManager().getCustomerRetrievalHashMap().get(p).countDown();
                }

                break;
            case ADDRESS:
                progressObject.setAddress(message);
                progressObject.setUserStep(CreateUserStep.CITY);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserCity());
                break;
            case CITY:
                progressObject.setCity(message);
                progressObject.setUserStep(CreateUserStep.STATE_PROVINCE);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserState());
                break;
            case STATE_PROVINCE:
                if (message.equalsIgnoreCase("NA")) {
                    progressObject.setStateOrProvince("");
                } else {
                    progressObject.setStateOrProvince(message);
                }

                progressObject.setUserStep(CreateUserStep.ZIP);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserZip());
                break;
            case ZIP:
                if (message.equalsIgnoreCase("NA")) {
                    progressObject.setZip("");
                } else {
                    progressObject.setZip(message);
                }

                progressObject.setUserStep(CreateUserStep.COUNTRY);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserCountry());
                break;
            case COUNTRY:
                progressObject.setCountry(message);
                progressObject.setUserStep(CreateUserStep.CC_NAME);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserName());
                break;
            case CC_NAME:
                progressObject.setCardName(message);
                progressObject.setUserStep(CreateUserStep.CC_NUMBER);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserCardNumber());
                break;
            case CC_NUMBER:
                progressObject.setCardNumber(message);
                progressObject.setUserStep(CreateUserStep.CC_MONTH);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserCardMonth());
                break;
            case CC_MONTH:
                progressObject.setCardMonth(message);
                progressObject.setUserStep(CreateUserStep.CC_YEAR);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserCardYear());
                break;
            case CC_YEAR:
                progressObject.setCardYear(message);
                progressObject.setUserStep(CreateUserStep.CC_CVC);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserCardCVC());
                break;
            case CC_CVC:
                progressObject.setCardCVC(message);

                p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserCreating());
                removePlayer(p);

                Bukkit.getScheduler().runTaskAsynchronously(magmaPay, () -> {
                    try {
                        String customerID = StripeImplementation.createCustomer(progressObject);

                        magmaPay.getCacheManager()
                                .addPlayer(p, new LocalPlayer(customerID, progressObject.getPinHash()));

                        p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserCreated());
                    } catch (CardException | AuthenticationException e) {
                        p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserValidationError()
                                .replace("<error>", e.getMessage()));
                    } catch (APIException | InvalidRequestException | APIConnectionException e) {
                        p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserStripeError()
                                .replace("<error>", e.getMessage()));
                    }

                    magmaPay.getCacheManager().getCustomerRetrievalHashMap().get(p).countDown();
                });

        }
    }

    boolean isInMap(Player p) {
        return createUserMap.containsKey(p);
    }

    private CreateUserProgressObject getPlayerObject(Player p) { return createUserMap.get(p); }

    public void addPlayer(Player p) {
        createUserMap.put(p, new CreateUserProgressObject());
        p.sendMessage(magmaPay.getLocalConfig().getMessageCreateUserEmail());
    }

    void removePlayer(Player p) {
        createUserMap.remove(p);
    }
}