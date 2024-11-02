package com.abhishikha;

import org.springframework.beans.factory.annotation.Value;

public interface NameOnly {
    String getFirstName();
    String getLastName();
    String getRole();

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();

    default String getFirstNameAndRole(){
        return getFirstName().concat(" ").concat(getRole());
    }

    @Value("#{@myBean.getFullNameViaBean(target)}")
    String getFullNameViaBean();
}