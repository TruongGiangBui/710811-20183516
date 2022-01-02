package entity.payment;

public class DomesticDebitCard extends PaymentCard {
    private String cardCode;
    private String owner;
    private String usingBank;
    private String dateExpired;

    public DomesticDebitCard(String cardCode, String owner, String usingBank, String dateExpired) {
        super();
        this.cardCode = cardCode;
        this.owner = owner;
        this.usingBank = usingBank;
        this.dateExpired = dateExpired;
    }
}
