# loan-management-app
A basic application that manages loans sanctioned by a certain entity to a certain different entity


configure the application.properties according to the database after cloning the project.
While running the application for the first time uncomment the following lines in the LoanScheduler class that are shown as commented below.

    @Override
    public void run(String... args) throws Exception {
        // Add loans
//        Loan loan1 = new Loan("L1", "C1", "LEN1", 10000, 10000, LocalDate.of(2023, 6, 5), 1, LocalDate.of(2023, 7, 5), 0.01);
//        Loan loan2 = new Loan("L2", "C1", "LEN1", 20000, 5000, LocalDate.of(2023, 6, 1), 1, LocalDate.of(2023, 8, 5), 0.01);
//        Loan loan3 = new Loan("L3", "C2", "LEN2", 50000, 30000, LocalDate.of(2023, 4, 4), 2, LocalDate.of(2023, 5, 4), 0.02);
//        Loan loan4 = new Loan("L4", "C3", "LEN2", 50000, 30000, LocalDate.of(2023, 4, 4), 2, LocalDate.of(2023, 5, 4), 0.02);
//
//        loanRepository.save(loan1);
//        loanRepository.save(loan2);
//        loanRepository.save(loan3);
//        loanRepository.save(loan4);
    }

- If using any SQL db once the application is run the default data will be entered inside the application and the above lines can be commented back if needed.
- However, in the case of an In-memory db these lines must be uncommented all the time and the application.properties must be configured accordingly.
