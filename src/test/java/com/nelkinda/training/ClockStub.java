package com.nelkinda.training;

import java.util.Date;

class ClockStub implements IClock {
  private final Date fixed;

  ClockStub(Date fixed) { this.fixed = fixed; }

  @Override
  public Date getDate() {
    return fixed;
  }
}
