package com.sensiblemetrics.api.sqoola.common.system.property;

@Component
@ConfigurationProperties("app")
public class MyAppProperties {
  private int refreshRate;
  private TimeUnit refreshTimeUnit;
  private Currency tradeCurrency;
    .............
}
