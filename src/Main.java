public class Main {
    public static void main(String[] args) {
        Cards cards = new Cards();
        Repository repository = new Repository();

        repository.addCard(431);
        repository.addCard(521);
        repository.addCard(112);
        repository.addCard(121);
        repository.addCard(461);
        repository.addCard(361);
        repository.addCard(272);
        repository.addCard(2101);
        repository.addCard(401);
        repository.addCard(2111);
        repository.addCard(2121);

        repository.print();


    }
}
