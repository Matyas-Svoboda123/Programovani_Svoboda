package accounts;

import person.AccountOwner;

public class StudentAccount extends BankAccount {
    private String schoolName;
    private double overdraftLimit = -5000;

    public StudentAccount(AccountOwner accountOwner, String accountNumber, String schoolName, String uuid) {
        this(accountOwner, accountNumber, 0, schoolName, uuid);
    }

    public StudentAccount(AccountOwner accountOwner, String accountNumber, double balance, String schoolName, String uuid) {
        super(accountOwner, accountNumber, uuid, balance);
        this.schoolName = schoolName;
    }

    // Bonus k vkladu řeší FeeService (DepositTransferService), proto zde add() nepřepisujeme.

    @Override
    public void sub(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (balance - amount < overdraftLimit) {
            throw new IllegalArgumentException("Exceeded overdraft limit of 5000");
        }
        this.balance -= amount;
    }

    public String getSchoolName() {
        return schoolName;
    }
}