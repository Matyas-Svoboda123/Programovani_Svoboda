package account;

import person.AccountOwner;

public class StudentAccount extends BankAccount{
    private String schoolName;
    private double OVERDRAFT_LIMIT = -5000;
    public StudentAccount(AccountOwner accountOwner, String accountNumber, String schoolName) {
        this(accountOwner, accountNumber,0,schoolName);
    }

    public StudentAccount(AccountOwner accountOwner, String accountNumber, double balance, String schoolName) {
        super(accountOwner, accountNumber, balance);
        this.schoolName = schoolName;

    }

    @Override
    public void add(double amount) {
        double bonusAmount = amount * 0.85;
        super.add(bonusAmount);
        super.add(amount);
    }

    @Override
    public void sub(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (balance - amount < OVERDRAFT_LIMIT) {
            throw new IllegalArgumentException("Exceeded overdraft limit of 5000");
        }
        this.balance -= amount;
    }

    public String getSchoolName() {
        return schoolName;
    }
}
