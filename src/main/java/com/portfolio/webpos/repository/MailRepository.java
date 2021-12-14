package com.portfolio.webpos.repository;

import com.portfolio.webpos.domain.Mail;

public interface MailRepository {
    Mail save(Mail mail);
    Mail findByMemberId(Long id);

}
