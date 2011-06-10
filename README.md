jValidate
===============

jValidate is a simple validation API for use in Java Object setter methods, or where ever programatic validation is required. The goal is to allow simple validations without a complete inversion of control or complicated XML configuration file.

Building / Testing / Installing
===============================

* Clone the repository
 * ```git clone git://github.com/mzsanford/jvalidate.git```
* (optional) Build the code and run the tests
 * ```mvn test```
* (optional) Check the test coverage
 * ```mvn emma:emma```
 * ```open target/site/emma/index.html```
* Build the jar file
 * ```mvn package```
 * ```# jar file in ./target/```


Usage
=====

All of the validations return the current validator object so they can be chained, like so:

```java
    public void setUsername(String username) throws ValidationException {
        new StringValidator(username).notNull()
                                     .minLength(5)
                                     .maxLength(20)
                                     .matches(Pattern.compile("^[a-z09_]+$"))
        this.username = username;
    }
```

it is fully expected that things like the minimum length, maximum length and pattern would be constants so they are not re-allocated for each call. In addition to `String` validation there are also validations for the common subclasses of `Number`, like:

```java
    public void setAge(int age) throws ValidationException {
        new IntegerValidator(age).min(14).max(20);
        this.age = age;
    }
```

Each validation throws a subclass of the base `ValidationException` to allow for better error messaging. A very savvy caller of the age example above might do something like this:

```java
    try {
        user.setAge(age);
    } catch (NumberTooLowException numberTooLowException) {
        System.out.println("Sorry, the COPPA requires you be at least 14 to register");
    } catch (NumberTooHighException numberTooHighException) {
        System.out.println("Sorry, your hand is blinking and a Sandman has been dispatched. See: http://bit.ly/lNVmX9");
    } catch (ValidationException validationException) {
        System.out.println("Invalid age, please try again");
    }
```

Links
==============

* [Javadocs](http://mzsanford.github.com/jvalidate/target/site/apidocs/)
* [Test Coverage](http://mzsanford.github.com/jvalidate/target/site/emma/index.html)

To do
===============

* MUST: Update methods with Javadoc comments
* MAY: Add Java annotations for static validations

Change Log
===============

* v1.0-SNAPSHOT (current pre-release version)
 * Initial validator base classes
 * `StringValidator` and the varioud flavors of `NumberValidator`
