package com.apress.batch.chapter10.domain;

public class CustomerContactUpdate extends CustomerUpdate {

    private final String emailAddress;
    private final String homePhone;
    private final String cellPhone;
    private final String workPhone;
    private final Integer notificationPreference;

    public CustomerContactUpdate(
            long customerId, String emailAddress,
            String homePhone, String cellPhone, String workPhone,
            Integer notificationPreference
    ) {
        super(customerId);
        this.emailAddress = emailAddress;
        this.homePhone = homePhone;
        this.cellPhone = cellPhone;
        this.workPhone = workPhone;
        this.notificationPreference = notificationPreference;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public Integer getNotificationPreference() {
        return notificationPreference;
    }
}
