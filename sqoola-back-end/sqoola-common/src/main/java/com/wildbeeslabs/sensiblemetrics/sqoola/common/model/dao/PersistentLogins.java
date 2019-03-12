package com.wildbeeslabs.sensiblemetrics.sqoola.common.model.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGINS_PERSISTENT")
public class PersistentLogins {

  @Id
  @Column(name = "SERIES")
  private String series;

  @Column(name = "USERNAME", nullable = false)
  private String username;

  @Column(name = "TOKEN", nullable = false)
  private String token;

  @Column(name = "LAST_USED", nullable = false)
  private Date lastUsed;

  //Setter and Getter methods
}
