import accounts.*;
import person.AccountOwner;
import person.AccountOwnerFactory;
import service.Transaction;
import transfer.AccountTransferService;
import transfer.DepositTransferService;
import transfer.TransferLoggerService;
import transfer.WithdrawTransferService;

public class Main {

    public static void main(String[] args) {

        // --- Majitelé ---
        AccountOwnerFactory ownerFactory = new AccountOwnerFactory();
        AccountOwner matyas = ownerFactory.createAccountOwner("Matyáš", "Svoboda");
        AccountOwner jan = ownerFactory.createAccountOwner("Jan", "Novak");
        AccountOwner eva = ownerFactory.createAccountOwner("Eva", "Dvorakova");

        // --- Účty ---
        BankAccount business = new BusinessAccountFactory().createBusinessAccountWithBalance(matyas, 450000);
        BankAccount current = new CurrentAccountFactory().createCurrentAccountWithBalance(jan, 100000);
        BankAccount savings = new SavingsAccountFactory().createSavingsWithBalance(jan, 20000);
        BankAccount student = new StudentAccountFactory().createStudentAccountWithBalance(eva, "SPŠ Ostrava", 1000);

        // --- Služby (všechny sdílí jeden logger) ---
        TransferLoggerService logger = new TransferLoggerService();
        AccountTransferService transferService = new AccountTransferService(logger);
        DepositTransferService depositService = new DepositTransferService(logger);
        WithdrawTransferService withdrawService = new WithdrawTransferService(logger);

        printBalances("Počáteční stav", business, current, savings, student);

        // 1) Převod z firemního účtu (poplatek 0,3 %)
        System.out.println("\n1) Převod business -> current: 25 000");
        transferService.transfer(business, current, 25000);

        // 2) Vklad na studentský účet (bonus 0,5 %)
        System.out.println("2) Vklad na studentský účet: 2 000");
        depositService.deposit(student, 2000);

        // 3) Výběr z firemního účtu (poplatek 1 %)
        System.out.println("3) Výběr z business účtu: 10 000");
        withdrawService.withdraw(business, 10000);

        // 4) Převod na spořicí účet (bonus 0,5 % z účtu)
        System.out.println("4) Převod current -> savings: 5 000");
        transferService.transfer(current, savings, 5000);

        // 5) Student jde do povoleného debetu (-5000)
        System.out.println("5) Výběr ze studentského účtu: 6 000 (do debetu)");
        withdrawService.withdraw(student, 6000);

        // --- Chybové scénáře: nic se nesmí změnit a nesmí se zalogovat ---
        System.out.println("\n--- Chybové scénáře ---");
        tryIt("Výběr 100 000 ze spořicího účtu (nedostatek peněz)", () -> withdrawService.withdraw(savings, 100000));
        tryIt("Student přes limit debetu", () -> withdrawService.withdraw(student, 50000));
        tryIt("Převod na stejný účet", () -> transferService.transfer(current, current, 100));
        tryIt("Záporný vklad", () -> depositService.deposit(current, -500));

        printBalances("\nKonečný stav", business, current, savings, student);

        // --- Historie ---
        System.out.println();
        logger.printAllTransactions();

        System.out.println("\nTransakce účtu " + current.getAccountNumber() + " (běžný účet):");
        for (Transaction t : logger.getTransactionsForAccount(current.getAccountNumber())) {
            System.out.println("  " + t.getType() + " " + t.getAmount() + " (poplatek " + t.getFee() + ")");
        }
        System.out.println("\nCelkem zalogováno transakcí: " + logger.getAllTransactions().size());
    }

    private static void printBalances(String title, BankAccount business, BankAccount current,
                                      BankAccount savings, BankAccount student) {
        System.out.println(title + ":");
        System.out.printf("  Business: %.2f%n  Current:  %.2f%n  Savings:  %.2f%n  Student:  %.2f%n",
                business.getBalance(), current.getBalance(), savings.getBalance(), student.getBalance());
    }

    private static void tryIt(String description, Runnable action) {
        try {
            action.run();
            System.out.println("  [!] Mělo selhat: " + description);
        } catch (IllegalArgumentException e) {
            System.out.println("  OK, odmítnuto: " + description + " -> " + e.getMessage());
        }
    }
}