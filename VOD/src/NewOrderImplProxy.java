public class NewOrderImplProxy implements NewOrder{
    NewOrder order;

    public NewOrderImplProxy(){
        this.order = new NewOrderImpl();
    }
    @Override
    public Order makeOrder(RegisteredUser user, Movie movie) {
        String givenPassword = "1234"; //TODO in real time - get user password as input
        // TODO this.user.Db.check password of this user
        boolean isPasswordCorrect = true;
        if (isPasswordCorrect){
            order.makeOrder(user, movie);
        } else{
            System.out.println("Wrong password");
        }
        return null;
    }
}
