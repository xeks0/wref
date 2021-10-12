package ru.wref.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Example how we use Java Class in model data kotlin
 * @param <T> type Id (Long, Int)
 */
@MappedSuperclass
public abstract class BaseModel<T extends Serializable> implements Serializable, IEntity<T> {

  private static final long serialVersionUID = 6835327780146020475L;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at")
  @Getter
  @Setter
  public Date updatedAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at")
  @Getter
  @Setter
  public Date createAt;

  @Column(name = "enabled_at")
  @Getter
  @Setter
  private Boolean enabledAt;

  @Column(name = "delete_at")
  @Getter
  @Setter
  private Boolean deleteAt;

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof BaseModel)
      if (this.getId() == ((BaseModel)obj).getId())
        return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    return getId() != null && getId().equals(((BaseModel) obj).getId());
  }

  @Override
  public int hashCode() {
    return getId() != null ? getId().hashCode() : super.hashCode();
  }
}
