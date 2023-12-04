package books;

import users.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoanBook extends Book implements bookDebt {

    private final Date requestDate;
    private final Date estimatedDate;
    private int daysOfDelay;
    private String status;
    private final User user;

    public LoanBook (String name, String author, Date requestDate, Date estimatedDate, String status, User user) {
        super(name, author);
        this.requestDate = requestDate;
        this.estimatedDate = estimatedDate;
        this.status = status;
        this.user = user;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Date getEstimatedDate() {
        return estimatedDate;
    }

    public String getStatus() {
        return status;
    }

    public User getUser() {
        return user;
    }

    public int getDaysOfDelay() {
        return daysOfDelay;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDaysOfDelay(int daysOfDelay) {
        this.daysOfDelay = daysOfDelay;
    }

    public void computeDaysOfDelay() {
        if (this.requestDate != null && this.estimatedDate != null) {
            long timeDifference = this.estimatedDate.getTime() - this.requestDate.getTime();
            this.setDaysOfDelay((int) (timeDifference / (1000 * 60 * 60 * 24)) - 10);
            if (this.getDaysOfDelay() > 0) {
                this.setStatus("OVERDUE");
                this.user.setDefaulter(true);
            }
            else {
                this.setStatus("IN ORDER");
                this.user.setDefaulter(false);
            }
        } else {
            throw new IllegalArgumentException("Both requestDate and estimatedDate must be non-null");
        }
    }

    public int computeMoneyOwed() {
        return Math.max(this.getDaysOfDelay(), 0);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return  super.toString() +
                "\nRequest Date: " + dateFormat.format(this.getRequestDate()) +
                "\nEstimated Date: " + dateFormat.format(this.getEstimatedDate()) +
                "\nStatus: " + this.getStatus() +
                "\nDays Of Delay: " + this.getDaysOfDelay() +
                "\nUser : " + this.getUser().getDNI() +
                "\nIs defaulter : " + (this.getUser().getDefaulter() ? "Yes" : "No") +
                "\nMoneyOwed: " + this.computeMoneyOwed();
    }
}
