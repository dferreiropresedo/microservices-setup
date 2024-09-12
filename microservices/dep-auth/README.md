# Authentication & Authorization

This module intends to automatically configure everything needed to add the authentication-related properties to the Spring
Boot configuration. Moreover, it can be toggled with the property `tools.oauth2.enabled` on the main configuration.

## How it is autoconfigured

The properties are loaded thanks to the class `DepAuthYamlLoader`, which is loaded through the `META-INF/spring.factories`.

The beans to be loaded are configured on the `DepAuthAutoconf` class, which is specified on the
`META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` file. There, the autoconfigured files
are declared.

## What does it do

This module contains the following:

* The URL that the server must invoke to check whether the JWT token received on the request is valid.
* Through the property `spring.config.import`, Spring Boot automatically imports the files specified on the location
established as an Environment Variable.
* Configure by default every endpoint as private and secured with OAuth2 with a JWT authentication, except
the actuators endpoints exposed by [Spring Boot Actuator](https://www.baeldung.com/spring-boot-actuators).
* In case the main project needs to make some endpoints public, it can be achieved declaring a `SecurityFilterChain` bean
as follows:

```java
  @Bean
  @Order(1) // An order must be specified in order to avoid conflicts
  public SecurityFilterChain publicPath(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .securityMatcher("/public-path")
        .authorizeHttpRequests(auth ->
            auth.anyRequest().permitAll()
        )
    ;
    return httpSecurity.build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain anotherPublicPath(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .securityMatcher("/another-public-path")
        .authorizeHttpRequests(auth ->
            auth.anyRequest().permitAll()
        )
    ;
    return httpSecurity.build();
  }
```
