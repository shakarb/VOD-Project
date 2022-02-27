public class NewOrderImplProxy implements NewOrder{
    NewOrder order;
    RegisteredUser user;

    public NewOrderImplProxy(RegisteredUser user){
        this.user = user;
        this.order = new NewOrderImpl();
    }
    @Override
    public Order makeOrder(RegisteredUser user, Movie movie) {
        String givenPassword = "1234"; //TODO get user password as input
        // this.user.Db.check password of this user
        boolean isPasswordCorrect = true; //TODO get result of password comparison from the DB somehow
        if (isPasswordCorrect){
            order.makeOrder(user, movie);
        } else{
            System.out.println("Wrong password");
        }
        return null;
    }
}
