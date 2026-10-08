package accounts.serialization;
import accounts.BankAccount;
public class BankAccountXmlSerializator {
    BankAccountSerializeFactory bankAccountSerializeFactory = new BankAccountSerializeFactory();
    public String serialize(BankAccount bankAccount){
        BankAccountSerialize bankAccountSerialize = bankAccountSerializeFactory.createBankAccountSerialize(bankAccount);
        StringBuilder builder = new StringBuilder();
        builder.append("<root>");
        builder.append("<bankAccountFactory");
        builder.append(bankAccountSerialize.accountNumber);
        builder.append("</bankAccountFactory");
        builder.append("</root>");

        return builder.toString();
    }
}
