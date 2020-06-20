
UUID Creator
======================================================

A Java library for generating and handling RFC-4122 UUIDs.

#### RFC-4122 UUIDs:

* __Version 1__: Time-based;
* __Version 2__: DCE Security;
* __Version 3__: Name-based (MD5);
* __Version 4__: Random-based;
* __Version 5__: Name-based (SHA1);
* __Version 6__: Time-ordered (proposed).

#### Non-standard GUIDs:

* __Prefix COMB__: combination of the creation millisecond (prefix) with random bytes;
* __Suffix COMB__: combination of the creation millisecond (suffix) with random bytes;
* __Short Prefix COMB__: combination the creation minute (prefix) with random bytes;
* __Short Suffix COMB__: combination the creation minute (suffix) with random bytes.

How to Use
------------------------------------------------------

### No time to read

If you just want a _random_ UUID, which is the most common case, use this single line of code:

```java
UUID uuid = UuidCreator.getRandomBased();
```

Or if you want a UUID that is _based on_ creation time, use this line of code:

```java
UUID uuid = UuidCreator.getTimeBased();
```

Or if you want a UUID that is _ordered by_ creation time, use this line:

```java
UUID uuid = UuidCreator.getTimeOrdered();
```

### Maven dependency

Add these lines to your `pom.xml`:

```xml
<!-- https://search.maven.org/artifact/com.github.f4b6a3/uuid-creator -->
<dependency>
  <groupId>com.github.f4b6a3</groupId>
  <artifactId>uuid-creator</artifactId>
  <version>2.4.0</version>
</dependency>
```
See more options in [maven.org](https://search.maven.org/artifact/com.github.f4b6a3/uuid-creator).

### Version 1: Time-based

The Time-based UUID has a timestamp and may have a hardware address.

The versions 1 and 6 use the same basic algorithm: both are based on date and time. The only difference is that the version 1 changes the timestamp byte order in a special layout.

```java
// Time-based
UUID uuid = UuidCreator.getTimeBased();
```

```java
// Time-based with hardware address
UUID uuid = UuidCreator.getTimeBasedWithMac();
```

Examples of time-based UUID:

```text
0edd7640-8eff-11e9-8649-972f32b091a1
0edd7641-8eff-11e9-8649-972f32b091a1
0edd7642-8eff-11e9-8649-972f32b091a1
0edd7643-8eff-11e9-8649-972f32b091a1
0edd7644-8eff-11e9-8649-972f32b091a1
0edd7645-8eff-11e9-8649-972f32b091a1
0edd7646-8eff-11e9-8649-972f32b091a1
0edd7647-8eff-11e9-8649-972f32b091a1
0edd7648-8eff-11e9-8649-972f32b091a1
0edd7649-8eff-11e9-8649-972f32b091a1
0edd764a-8eff-11e9-8649-972f32b091a1
0edd764b-8eff-11e9-8649-972f32b091a1
0edd764c-8eff-11e9-8649-972f32b091a1
0edd764d-8eff-11e9-8649-972f32b091a1
0edd764e-8eff-11e9-8649-972f32b091a1
0edd764f-8eff-11e9-8649-972f32b091a1
       ^ look

|-----------------|----|-----------|
     timestamp    clkseq  node id
```

### Version 2: DCE Security

The DCE Security<sup>[6]</sup> is a time-based UUID that also has a local domain and a local identifier.

```java
// DCE Security
UuidLocalDomain localDomain = UuidLocalDomain.LOCAL_DOMAIN_PERSON;
int localIdentifier = 1701;
UUID uuid = UuidCreator.getDceSecurity(localDomain, localIdentifier);
```

```java
// DCE Security with hardware address
UuidLocalDomain localDomain = UuidLocalDomain.LOCAL_DOMAIN_GROUP;
int localIdentifier = 1984;
UUID uuid = UuidCreator.getDceSecurityWithMac(localDomain, localIdentifier);
```

List of predefined local domains:

* `UuidLocalDomain.LOCAL_DOMAIN_PERSON`: Local identifier is member of a [user domain](https://en.wikipedia.org/wiki/User_identifier);
* `UuidLocalDomain.LOCAL_DOMAIN_GROUP`: Local identifier is member of a [group domain](https://en.wikipedia.org/wiki/Group_identifier);
* `UuidLocalDomain.LOCAL_DOMAIN_ORG`: Local identifier is member of an organization domain.

### Version 3: Name-based (MD5)

The version 3 UUID is generated by a MD5 hash algorithm.

```java
// Name-based MD5
String name = "https://github.com/";
UUID uuid = UuidCreator.getNameBasedMd5(name);
```

```java
// Name-based MD5 using a name space
UuidNamespace namespace = UuidNamespace.NAMESPACE_URL;
String name = "https://github.com/";
UUID uuid = UuidCreator.getNameBasedMd5(namespace, name);
```

List of predefined [name spaces](https://en.wikipedia.org/wiki/Namespace):

* `UuidNamespace.NAMESPACE_DNS`: Name string is a [fully-qualified domain name](https://en.wikipedia.org/wiki/Domain_Name_System);
* `UuidNamespace.NAMESPACE_URL`: Name string is a [URL](https://en.wikipedia.org/wiki/URL);
* `UuidNamespace.NAMESPACE_ISO_OID`: Name string is an [ISO Object ID](https://en.wikipedia.org/wiki/Object_identifier);
* `UuidNamespace.NAMESPACE_X500_DN`: Name string is an [X.500 Distinguished Name](https://en.wikipedia.org/wiki/X.500) (in DER or a text output format).

### Version 4: Random-based

The random-based UUID is a random array of 16 bytes.

The default random generator is a thread local `java.security.SecureRandom`.

```java
// Random using the default SecureRandom generator
UUID uuid = UuidCreator.getRandomBased();
```

Examples of random-based UUID:

```text
38b37e02-d978-42cc-b39f-699d41ad6b13
4241488c-c17a-48c9-bf82-aa0afe675c2f
03e26434-323c-411c-bedf-34f8b99889e8
625b9fa3-21d1-4ddc-a5d7-97d277fbe268
b60a97a3-c1f9-48e9-8afe-d8505fd3fe58
071105f2-6c78-4fbb-a5c1-c8f48afd1a76
5bf70214-67e4-4f31-b3fb-cb8a8366d158
1dd86663-2263-443a-a49c-29d6d877b3f4
1172b75d-d55d-436c-9832-63b4c64e1813
279a12eb-d411-45b9-85cc-98ff692fb0e2
3c18b7e9-75a3-4f58-b608-9bdadff6ecd9
c136ac3c-b25e-414d-9d9e-0821b90bfe14
3f020533-fec9-4a94-9287-b8be5b2dda77
9d887b20-3394-4350-aba4-621ef6ea837a
9e71dd3d-c839-46ee-a249-d375f3460583
246bd1c9-cc6d-48b7-bd88-a5035d597842

|----------------------------------|
            randomness
```

### Version 5: Name-based (SHA-1)

The version 5 UUID is generated by a SHA-1 hash algorithm.


```java
// Name-based SHA-1
String name = "https://github.com/";
UUID uuid = UuidCreator.getNameBasedSha1(name);
```

```java
// Name-based SHA-1 using a name space
UuidNamespace namespace = UuidNamespace.NAMESPACE_URL;
String name = "https://github.com/";
UUID uuid = UuidCreator.getNameBasedSha1(namespace, name);
```

List of predefined [name spaces](https://en.wikipedia.org/wiki/Namespace):

* `UuidNamespace.NAMESPACE_DNS`: Name string is a [fully-qualified domain name](https://en.wikipedia.org/wiki/Domain_Name_System);
* `UuidNamespace.NAMESPACE_URL`: Name string is a [URL](https://en.wikipedia.org/wiki/URL);
* `UuidNamespace.NAMESPACE_ISO_OID`: Name string is an [ISO Object ID](https://en.wikipedia.org/wiki/Object_identifier);
* `UuidNamespace.NAMESPACE_X500_DN`: Name string is an [X.500 Distinguished Name](https://en.wikipedia.org/wiki/X.500) (in DER or a text output format).

### Version 6: Time-ordered (proposed)

The Time-ordered<sup>[4]</sup> <sup>[5]</sup> UUID has a timestamp and may have a hardware address. 

The timestamp bits are kept in the original order, unlike the time-based UUID.

```java
// Time-ordered
UUID uuid = UuidCreator.getTimeOrdered();
```

```java
// Time-ordered with hardware address
UUID uuid = UuidCreator.getTimeOrderedWithMac();
```

Examples of time-ordered UUID:

```text
1e98eff0-eddc-6470-a649-ad1cde652e10
1e98eff0-eddc-6471-a649-ad1cde652e10
1e98eff0-eddc-6472-a649-ad1cde652e10
1e98eff0-eddc-6473-a649-ad1cde652e10
1e98eff0-eddc-6474-a649-ad1cde652e10
1e98eff0-eddc-6475-a649-ad1cde652e10
1e98eff0-eddc-6476-a649-ad1cde652e10
1e98eff0-eddc-6477-a649-ad1cde652e10
1e98eff0-eddc-6478-a649-ad1cde652e10
1e98eff0-eddc-6479-a649-ad1cde652e10
1e98eff0-eddc-647a-a649-ad1cde652e10
1e98eff0-eddc-647b-a649-ad1cde652e10
1e98eff0-eddc-647c-a649-ad1cde652e10
1e98eff0-eddc-647d-a649-ad1cde652e10
1e98eff0-eddc-647e-a649-ad1cde652e10
1e98eff0-eddc-647f-a649-ad1cde652e10
                 ^ look

|-----------------|----|-----------|
     timestamp    clkseq  node id
```

### Prefix COMB (non-standard)

The Prefix COMB<sup>[7]</sup> is a modified random-based UUID that replaces the FIRST 6 bytes of the MOST significant bits.

The PREFIX is the creation millisecond (Unix epoch).

```java
// Prefix COMB
UUID uuid = UuidCreator.getPrefixComb();
```

Examples of Prefix COMB:

```text
01720b5c-bf10-423f-9b20-1f88a7e2763a
01720b5c-bf10-43bf-a639-9b511243507f
01720b5c-bf10-4b47-9925-f96f0e197ba5
01720b5c-bf10-49ba-84c0-7ace9ae546bb
01720b5c-bf10-4327-b62a-2a242dcc197f
01720b5c-bf10-47cc-b631-4f262f500172
01720b5c-bf10-42ca-b063-28d5329b8d90
01720b5c-bf10-4442-876b-2710215d41e1
01720b5c-bf11-4a08-a63f-4495659603b4 < millisecond changed
01720b5c-bf11-49a8-8339-8e3b429fc6fb
01720b5c-bf11-4879-81f9-97194a331e3f
01720b5c-bf11-497f-a599-c52a8c692107
01720b5c-bf11-403c-a1e1-4f112539f97a
01720b5c-bf11-4e97-a9e3-59215d3d5fb1
01720b5c-bf11-4135-bb7f-422ec3c33cca
01720b5c-bf11-4460-9a62-bcf16e35b418
01720b5c-bf11...
            ^ look
            
|------------|---------------------|
    prefix         randomness
```

### Suffix COMB (non-standard)

The Suffix COMB<sup>[7]</sup> is a modified random-based UUID that replaces the LAST 6 bytes of the LEAST significant bits.

The SUFFIX is the creation millisecond (Unix epoch).

```java
// Suffix COMB
UUID uuid = UuidCreator.getSuffixComb();
```

Examples of Suffix COMB:

```text
0c7fb3f5-cf99-4dc4-942a-01720b5cbf0c
4fa8ccdc-75c5-43ae-bf2f-01720b5cbf0c
3fdc05c0-8787-4072-b710-01720b5cbf0c
e1eea5da-5afa-4273-80c3-01720b5cbf0c
d7df7ec2-3a1a-4c9b-9fbc-01720b5cbf0c
119a1f87-59d1-4f08-b7e9-01720b5cbf0c
d0bb8776-37fa-47d4-978f-01720b5cbf0c
867a1ec3-f8de-46ec-b3a4-01720b5cbf0c
1739a555-5a45-44fd-9331-01720b5cbf0d < millisecond changed
43d9c807-2286-4bd7-8263-01720b5cbf0d
643653e4-8ae2-43bf-be16-01720b5cbf0d
e1f40089-7cf0-42f1-a27a-01720b5cbf0d
f68b0dfd-fe74-42d3-a4c4-01720b5cbf0d
adb7f7eb-760f-427d-ba6e-01720b5cbf0d
4b69b874-f2b9-4b1a-ad31-01720b5cbf0d
612cfbc3-70c0-4301-ae95-01720b5cbf0d
                     ...01720b5cbf0d
                                   ^ look
                                   
|----------------------|-----------|
       randomness         suffix
```

### Short Prefix COMB (non-standard)

The Short Prefix COMB<sup>[10]</sup> is a modified random-based UUID that replaces 2 bytes of the MOST significant bits.

The PREFIX is the creation minute (Unix epoch). It wraps around every 45 days (2^16/60/24 = ~45).

```java
// Short Prefix COMB
UUID uuid = UuidCreator.getShortPrefixComb();
```

Examples of Short Prefix COMB:

```text
2fe8ef26-5684-4192-aab6-fc6a0aaa191c
2fe808c2-bd77-4338-8954-e2152ae8a8df
2fe8c7c2-2c1e-4f7d-88fa-e3d115d7e4c9
2fe84e2f-b8ed-4a1f-99be-742e781067f7
2fe85dad-4e7f-4447-b035-23882d69027d
2fe810cb-fa4d-4d2c-9e4f-b122d0d19391
2fe8a8c1-b039-477b-8b63-483eb986434e
2fe839c9-b1b7-43c7-88c5-09fda1ef30e6
2fe8a971-e3ac-4f3b-858a-1aad577e8c36
2fe87c36-9e81-40c8-bff2-1bf9956c0d32
2fe86aca-f113-4ef4-8b69-1b5de35d0832
2fe8ec69-7acc-4cff-91c9-f658b331ee67
2fe88b94-993f-4176-9991-1f9e778a79a0
2fe8b041-d0de-4552-b6b5-449a8ee32134
2fe8da35-ce9d-4d4a-90e5-c2a4c89f18c7
2fe8...

|---|------------------------------|
prefix         randomness
```

### Short Suffix COMB (non-standard)

The Short Suffix COMB GUID<sup>[10]</sup> is a modified random-based UUID that replaces 2 bytes of the LEAST significant bits.

The SUFFIX is the creation minute (Unix epoch). It wraps around every 45 days (2^16/60/24 = ~45).

```java
// Short Suffix COMB
UUID uuid = UuidCreator.getShortSuffixComb();
```

Examples of Short Suffix COMB:

```text
0c06fac1-fe6a-4efa-ad45-2fe861065351
2d505d8f-10e6-42b3-9be3-2fe8064a65e9
d2eed78b-3277-42e4-ae58-2fe8e6f09523
5e471a1c-6cd8-4112-a9cb-2fe81873ba5f
5777ecca-471d-4926-8c71-2fe898083dc9
2c9602c6-d3e7-4cce-b687-2fe8681f205e
7cc02c02-c867-4bbb-a130-2fe87bd427cc
033d4881-f059-4171-bc83-2fe89e5a2bed
727f4de4-0c62-4132-b3ce-2fe824ff3d5b
e2df0d73-cc2b-455f-a9e4-2fe8bf4bf0f0
ad0a704a-0d03-48b5-a7eb-2fe8e01165d7
94d785b2-eb62-4eaa-88e7-2fe8fc5c3478
4f2a5300-938d-44d2-9b2a-2fe83a86787e
384df3b4-36b7-4154-961a-2fe87bbbe5f4
53ab5fd3-31ee-4cb9-8779-2fe8a887b3be
                     ...2fe8...

|----------------------|---|-------|
       randomness      suffix
```

System properties and environment variables
------------------------------------------------------

### Node identifier

The `nodeid` property is used by the `DefaultNodeIdentifierStrategy`. If this property or variable exists, its value is returned. Otherwise, a random value is returned. In the default strategy the `nodeid` property is a *preferred value* for the node identifier, that is, it overrides the random value generated by the node identifier strategy. It may be useful if you want to identify each single machine by yourself, instead of allowing the algorithm do it for you. It accepts 12 hexadecimal chars.

The simplest way to avoid collisions is to ensure that each generator has its own node identifier.

* Using system property:

```bash
// append to VM arguments
-Duuidcreator.nodeid="c0da06157723"
```

* Using environment variable:

```bash
// append to /etc/environment or ~/.profile
export UUIDCREATOR_NODEID="c0da06157723"
```

Implementation
------------------------------------------------------

### Format

The canonical format is a hexadecimal string that contains 5 groups separated by dashes.

```
Canonical format

 00000000-0000-v000-m000-000000000000
|1-------|2---|3---|4---|5-----------|

1: 8 chars
2: 4 chars
3: 4 chars
4: 4 chars
5: 12 chars

v: version number
m: variant number (sharing bits with the clock-sequence)
```

### Representation

The `java.util.UUID`[<sup>&#x2197;</sup>](https://docs.oracle.com/javase/7/docs/api/java/util/UUID.html) class represents a UUID with two `long` fields, called most significant bits (MSB) and least significant bits (LSB).

```
Representation in java.util.UUID

 00000000-0000-v000-m000-000000000000
|msb---------------|lsb--------------|

msb: Most Significant Bits
lsb: Least Significant Bits

v: version number
m: variant number (sharing bits with clock-sequence)
```

###  Time-based

The Time-based UUID has three main parts: timestamp, clock-sequence and node identifier.

```
Time-based UUID structure

 00000000-0000-v000-m000-000000000000
|1-----------------|2---|3-----------|

1: timestamp
2: clock-sequence
3: node identifier
```

#### Timestamp

The timestamp is a value that represents date and time. It has 4 subparts: low timestamp, middle timestamp, high timestamp and version number.

```
Standard timestamp arrangement

 00000000-0000-v000-m000-000000000000
|1-------|2---|3---|

1: timestamp low      *
2: timestamp mid     ***
3: timestamp high   *****
```

In the version 1 UUID the timestamp bytes are rearranged so that the highest bits are put in the end of the array of bits and the lowest ones in the beginning. The standard _timestamp resolution_ is 1 second divided by 10,000,000. The timestamp is the amount of 100 nanoseconds intervals since 1582-10-15. Since the timestamp has 60 bits (unsigned), the greatest date and time that can be represented is 5236-03-31T21:21:00.684Z.

In this implementation, the timestamp has milliseconds accuracy, that is, it uses `System.currentTimeMillis()`[<sup>&#x2197;</sup>](https://docs.oracle.com/javase/7/docs/api/java/lang/System.html#currentTimeMillis()) to get the current milliseconds. An internal _counter_ is used to _simulate_ the standard timestamp resolution of 10 million intervals per second.

You can create any strategy that implements the `TimestampStrategy` in the case that none of the strategies provided suffices.

##### Counter

The counter range is from 0 to 9,999. Every time a request is made at the same millisecond, the counter is increased by 1. Each counter value corresponds to a 100 nanosecond interval. The timestamp is calculated with this formula: MILLISECONDS * 10,000 + COUNTER. 

The timestamp counter is not instantiated with ZERO, but with a random number between 0 and 255. The 8 least significant bits of the counter are random. Every time a new counter value is required, this number is incremented by 1. When the limit of 9,999 is reached within the same interval, the counter is restarted to ZERO and an exception is raised. The first counter value for the next interval is the least 8 significant bits of the previous counter value.

##### Overrun exception

The overrun exception is thrown when too many requests are made within the same millisecond interval. If the timestamp counter reaches the maximum of 10,000 (0 to 9,999), it is restarted to ZERO and an exception is thrown to the developer decide how to handle it.

The exception is raised to comply the RFC-4122, that requires:

```text
   If a system overruns the generator by requesting too many UUIDs
   within a single system time interval, the UUID service MUST either
   return an error, or stall the UUID generator until the system clock
   catches up.
```

#### Clock sequence

The clock sequence helps to avoid duplicates. It comes in when the system clock is backwards or when the node identifier changes. It also expands the amount of UUIDs that can be created at the same millisecond.

The first bits of the clock sequence are multiplexed with the variant number of the RFC-4122. Because of that, it has a range from 0 to 16383 (0x0000 to 0x3FFF). This value is increased by 1 if more than one request is made by the system at the same timestamp or if the timestamp is backwards. In other words, it acts like a counter that is incremented whenever the timestamp repeats or may be repeated.

In the `DefaultClockSequenceStrategy` each generator on the same JVM receives a unique clock sequence value. This local uniqueness is managed by the class `ClockSequenceController`.

You can create any strategy that implements the `ClockSequenceStrategy` in the case that none of the strategies are good for you.

#### Node identifier

The node identifier is an IEEE 802 MAC address, usually the host machine address. But if no address is available or if the usage of MAC address is not desired, the standard allows the usage of a random generated value. In this library the default behavior is to use a random node identifier generated by a secure random generator.

It's also possible to use a _preferred node identifier_ by setting a system property or environment variable. The system property is `uuidcreator.nodeid` and the environment variable is `UUIDCREATOR_NODEID`. All UUIDs created on a machine will use the value present in the property or variable.

The simplest way to avoid collisions is to ensure that each generator has its own node identifier.

You can also create your own strategy that implements the `NodeIdentifierStrategy`.

##### Hardware address

The hardware address node identifier is the MAC address associated with the host name. If that MAC address can't be found, it uses the first MAC address that is up and running. If no MAC is found, a random number is used.

### Time-ordered

The Time-ordered UUID inherits the same characteristics of the time-based UUID. The only difference is that the timestamp bits are not rearranged as the Time-based UUID does. <sup>[4]</sup> <sup>[5]</sup>

```
Time-ordered timestamp arrangement

 00000000-0000-v000-m000-000000000000
|1-------|2---|3---|

1: timestamp high   *****
2: timestamp mid     ***
3: timestamp low      *
```

#### Time-based vs Time-ordered

The RFC-4122 splits the timestamp bits into three parts: high, middle, and low. Then it reverses the order of these three parts to create a time-based UUID. The reason for this layout is not documented in the RFC-4122.

In the time-ordered, the timestamp bits are kept in the original layout. See the example below.

Instant:

```
 2019-09-22T17:52:55.033Z
```

Instant's timestamp in hexadecimal:

```
 01e9dd61ce117e90
```

Timestamp split into high, middle and low bits:

```
 _1e9 dd61 ce117e90
 _hhh mmmm llllllll

h: high bits
m: middle bits
l: low bit
_: placeholder for version bits
```

Timestamp in time-based layout (version 1):

```
 ce117e90-dd61-11e9-8080-39d701ba2e7c
 llllllll-mmmm-vhhh-ssss-nnnnnnnnnnnn

l: low bits
m: middle bits
h: high bits
v: version
s: clock sequence bits
n: node id bits
```

Timestamp in time-ordered layout (version 6):

```
 1e9dd61c-e117-0e90-8080-39d701ba2e7c
 hhhmmmml-llll-vlll-ssss-nnnnnnnnnnnn

l: low bits
m: middle bits
h: high bits
v: version
s: clock sequence bits
n: node id bits
```

### Random-based

The random-based UUID is a random array of 16 bytes.

The random-based factory uses `java.security.SecureRandom` to get 'cryptographic quality random' bytes as the standard requires.

If the default `SecureRandom` is not desired, any instance of `java.util.Random` can be used.

You can also implement a custom `RandomStrategy` that uses any random generator you want, not only instances of `java.util.Random`.

All COMB creators inherit the same characteristics of the random-based creator.

### Name-based

There are two types of name-based UUIDs: MD5 and SHA-1. The MD5 is registered as version 3. And the SHA-1 is registered as version 5.

Two arguments are needed to generate a name-based UUID: a name space and a name.

The name space is an optional UUID object.

The name argument may be a string or a byte array.

### DCE Security

The DCE Security<sup>[6]</sup> UUID inherits the same characteristics of the time-based UUID.

The difference is that it also contains information of local domain and local identifier. A half of the timestamp is replaced by a local identifier number. And half of the clock sequence is replaced by a local domain number.

Configuration examples
------------------------------------------------------

This section contains a lot of examples on how to setup the internal UUID creators for specific needs.

Most cases you don't have to configure the internal creators directly. All the UUID types can be generated using a single line of code, for example, `UuidCreator.getTimeBased()`.

But if you want, for example, a time-based UUID that has a more accurate timestamp, you can implement another `TimestampStrategy` and pass it to the `TimeBasedUuidCreator`.

All UUID creators are configurable via [method chaining](https://en.wikipedia.org/wiki/Method_chaining).

### Time-based

All the examples in this subsection are also valid for Time-ordered and DCE Security UUIDs.

##### Timestamp

```java

// with fixed instant (now)
Instant instant = Instant.now();
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withInstant(instant)
    .create();

// with fixed Unix epoch millisecond (current Unix milliseconds)
long milliseconds = System.currentTimeMillis();
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withUnixMilliseconds(milliseconds);
    .create();

```

##### Timestamp strategy

```java

// with timestamp provided by a custom strategy
TimestampStrategy customStrategy = new CustomTimestampStrategy();
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withTimestampStrategy(customStrategy)
    .create();

```

##### Node identifier

```java

// with hardware address (first MAC found)
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withMacNodeIdentifier()
    .create();
    
// with fixed number (48 bits)
long number = 0x0000aabbccddeeffL;
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withNodeIdentifier(number)
    .create();
    
// with fixed byte array (6 bytes)
byte[] bytes = {(byte) 0xaa, (byte) 0xbb, (byte) 0xcc, (byte) 0xdd, (byte) 0xee, (byte) 0xff};
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withNodeIdentifier(bytes)
    .create();
    
// with host IP address
try {
	byte[] ip = InetAddress.getLocalHost().getAddress();
	UUID uuid = UuidCreator.getTimeBasedCreator()
	    .withNodeIdentifier(ip)
	    .create();
} catch (UnknownHostException | SocketException e) {
	// treat exception
}

// with host fingerprint (SHA-256 hash of OS + JVM + Network + Resources + locale + timezone)
long fingerprint = FingerprintUtil.getFingerprint();
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withNodeIdentifier(fingerprint)
    .create();

```

##### Node identifier strategy

```java

// with node identifier provided by a custom strategy
NodeIdentifierStrategy customStrategy = new CustomNodeIdentifierStrategy();
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withNodeIdentifierStrategy(customStrategy)
    .create();

```

##### Clock sequence

```java

// with fixed number (0 to 16383)
int number = 2039;
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withClockSequence(number)
    .create();
    
// with fixed byte array (2 bytes)
byte[] bytes = {(byte) 0xaa, (byte) 0xbb};
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withClockSequence(bytes)
    .create();

```

##### Clock sequence strategy

```java

// with clock sequence provided by a custom strategy
ClockSequenceStrategy customStrategy = new CustomClockSequenceStrategy();
UUID uuid = UuidCreator.getTimeBasedCreator()
    .withClockSequenceStrategy(customStrategy)
    .create();

```

### Random-based

All the examples in this subsection are also valid for COMBs.

##### Random generator

```java

// with another random generator that is an instance of `java.util.Random`
Random random = new Random();
UUID uuid = UuidCreator.getRandomBasedCreator()
    .withRandomGenerator(random)
    .create();

// with another random strategy that uses an instance of `java.util.Random`
// this example is equivalent to the previous one, but more verbose
RandomStrategy strategy = new OtherRandomStrategy(new Random());
UUID uuid = UuidCreator.getRandomBasedCreator()
    .withRandomStragety(strategy)
    .create();

```

##### Random generator strategy

```java
    
// with a CUSTOM random strategy that uses any random generator
RandomStrategy customStrategy = new CustomRandomStrategy();
UUID uuid = UuidCreator.getRandomBasedCreator()
    .withRandomStrategy(customStrategy)
    .create();

// with an ANONYMOUS random strategy that uses any random generator
import com.github.niceguy.random.AwesomeRandom;
RandomBasedUuidCreator creator = UuidCreator.getRandomBasedCreator()
		.withRandomStrategy(new RandomStrategy() {
			private final AwesomeRandom awesomeRandom = new AwesomeRandom();
			@Override public void nextBytes(byte[] bytes) {
				this.awesomeRandom.nextBytes(bytes);
			}
		});
UUID uuid = creator.create();

```

### Name-based

All the examples in this subsection are also valid for SHA-1 UUIDs.

```java

// without name space
String name = "Paul Smith";
UUID uuid = UuidCreator.getNameBasedMd5Creator()
    .create(name);

// with a name space
UuidNamespace namespace = UuidNamespace.NAMESPACE_URL;
String name = "www.github.com";
UUID uuid = UuidCreator.getNameBasedMd5Creator()
    .withNamespace(namespace)
    .create(name);

// with a CUSTOM name space
// In this example, the category "products/books" is transformed into a custom namespace
UUID customNamespace = UuidCreator.getNameBasedMd5("products/books")
String name = "War and Peace - Leo Tolstoy";
UUID uuid = UuidCreator.getNameBasedMd5Creator()
    .withNamespace(customNamespace)
    .create(name);

```

### DCE Security

```java

// with fixed local domain (POSIX User ID)
UuidLocalDomain localDomain = UuidLocalDomain.LOCAL_DOMAIN_PERSON;
int localIdentifier = 1701;
UUID uuid = UuidCreator.getDceSecurityCreator()
    .withLocalDomain(localDomain)
    .create(localIdentifier);
    
// with fixed CUSTOM local domain
byte customLocalDomain = (byte) 54;
int localIdentifier = 1492;
UUID uuid = UuidCreator.getDceSecurityCreator()
    .withLocalDomain(customLocalDomain)
    .create(localIdentifier);

```

Benchmark
------------------------------------------------------

This section shows benchmarks using JMH v1.23.

```text
-----------------------------------------------------------------------------------
THROUGHPUT (operations/millis)
-----------------------------------------------------------------------------------
Benchmark                                Mode  Cnt      Score     Error   Units
-----------------------------------------------------------------------------------
Throughput.Eaio_TimeBased (*)           thrpt    5  20675,885 ± 288,678  ops/ms
Throughput.Java_RandomBased             thrpt    5   2043,404 ±  29,534  ops/ms
Throughput.UuidCreator_NameBasedMd5     thrpt    5   3542,077 ±  34,960  ops/ms
Throughput.UuidCreator_NameBasedSha1    thrpt    5   2723,757 ±  35,945  ops/ms
Throughput.UuidCreator_PrefixComb       thrpt    5   2618,561 ±  40,111  ops/ms
Throughput.UuidCreator_RandomBased      thrpt    5   2011,758 ±  25,476  ops/ms
Throughput.UuidCreator_ShortPrefixComb  thrpt    5   2042,027 ±  25,951  ops/ms
Throughput.UuidCreator_TimeBased        thrpt    5  17404,991 ± 306,446  ops/ms
Throughput.UuidCreator_TimeOrdered      thrpt    5  17305,834 ± 363,300  ops/ms
-----------------------------------------------------------------------------------
Total time: 00:13:23
-----------------------------------------------------------------------------------
```

```text
-----------------------------------------------------------------------------------
AVERAGE TIME (nanos/operation)
-----------------------------------------------------------------------------------
Benchmark                                Mode  Cnt    Score    Error  Units
-----------------------------------------------------------------------------------
AverageTime.Eaio_TimeBased (*)           avgt    5   48,500 ±  1,371  ns/op
AverageTime.Java_RandomBased             avgt    5  489,989 ±  5,458  ns/op
AverageTime.UuidCreator_NameBasedMd5     avgt    5  280,546 ±  1,840  ns/op
AverageTime.UuidCreator_NameBasedSha1    avgt    5  376,271 ±  3,414  ns/op
AverageTime.UuidCreator_PrefixComb       avgt    5  381,903 ±  7,952  ns/op
AverageTime.UuidCreator_RandomBased      avgt    5  493,900 ± 12,183  ns/op
AverageTime.UuidCreator_ShortPrefixComb  avgt    5  489,810 ±  6,250  ns/op
AverageTime.UuidCreator_TimeBased        avgt    5   56,973 ±  1,846  ns/op
AverageTime.UuidCreator_TimeOrdered      avgt    5   56,338 ±  1,330  ns/op
-----------------------------------------------------------------------------------
Total time: 00:13:23
-----------------------------------------------------------------------------------
```

These external generators are used for comparison:

- com.eaio.uuid.UUID (for time-based UUID);
- java.util.UUID (for random UUID).

Benchmarks executed in a PC with Ubuntu 20.04, JVM 8, CPU Intel i5-3330 and 8GB RAM.

You can find the benchmark source code at [uuid-creator-benchmark](https://github.com/fabiolimace/uuid-creator-benchmark).

(*) The UUID generated by EAIO is not an instance of `java.util.UUID`.

Related projects
------------------------------------------------------

* [uuid-creator-benchmark](https://github.com/fabiolimace/uuid-creator-benchmark): Benchmarks for uuid-creator

* [uuid-creator-database](https://github.com/fabiolimace/uuid-creator-database): Tests for uuid-creator in databases

References
------------------------------------------------------

[1]. Universally unique identifier. Wikipedia.

[2]. A Universally Unique IDentifier (UUID). RFC-4122.

[3]. To UUID or not to UUID?

[4]. Store UUID in an optimized way.

[5]. UUID "Version 6": the version RFC 4122 forgot.

[6]. DCE 1.1: Security-Version (Version 2) UUIDs. The Open Group.

[7]. The Cost of GUIDs as Primary Keys (COMB Algorithm)

[8]. How to Generate Sequential GUIDs for SQL Server in .NET

[9]. ULID Specification - Universally Unique Lexicographically Sortable Identifier

[10]. Sequential UUID Generators

[1]: https://en.wikipedia.org/wiki/Universally_unique_identifier
[2]: https://tools.ietf.org/html/rfc4122
[3]: https://www.percona.com/blog/2007/03/13/to-uuid-or-not-to-uuid
[4]: https://www.percona.com/blog/2014/12/19/store-uuid-optimized-way
[5]: https://bradleypeabody.github.io/uuidv6
[6]: http://pubs.opengroup.org/onlinepubs/9696989899/chap5.htm#tagcjh_08_02_01_01
[7]: http://www.informit.com/articles/printerfriendly/25862
[8]: https://blogs.msdn.microsoft.com/dbrowne/2012/07/03/how-to-generate-sequential-guids-for-sql-server-in-net
[9]: https://github.com/ulid/spec
[10]: https://blog.2ndquadrant.com/sequential-uuid-generators/

More links
------------------------------------------------------

* [A brief history of the UUID](https://segment.com/blog/a-brief-history-of-the-uuid)

* [Syntax and semantics of the DCE variant of Universal Unique Identifiers (The OpenGroup)](https://pubs.opengroup.org/onlinepubs/9629399/apdxa.htm)

* [How is a Time-based UUID / GUID made](https://www.famkruithof.net/guid-uuid-timebased.html)

* [Sequential UUID Generators](https://blog.2ndquadrant.com/sequential-uuid-generators/)

* [Sequential UUID Generators on SSD ](https://www.2ndquadrant.com/en/blog/sequential-uuid-generators-ssd/)

* [Be Careful with UUID or GUID as Primary Keys](https://news.ycombinator.com/item?id=14523523)

* [Ordered-uuid - npmjs package](https://www.npmjs.com/package/ordered-uuid)

* [To UUID or not to UUID](http://stereobooster.github.io/to-uuid-or-not-to-uuid)

* [Primary Keys: IDs versus GUIDs](https://blog.codinghorror.com/primary-keys-ids-versus-guids/)

* [GUIDs are globally unique, but substrings of GUIDs aren’t](https://blogs.msdn.microsoft.com/oldnewthing/20080627-00/?p=21823)

* [MySQL Performance When Using UUID For Primary Key](https://blog.programster.org/mysql-performance-when-using-uuid-for-primary-key)

* [What are the performance improvement of Sequential Guid over standard Guid?](https://stackoverflow.com/questions/170346/what-are-the-performance-improvement-of-sequential-guid-over-standard-guid)

* [Auto increment keys vs. UUID](https://medium.com/@Mareks_082/auto-increment-keys-vs-uuid-a74d81f7476a)

* [GUIDs as fast primary keys under multiple databases](https://www.codeproject.com/Articles/388157/GUIDs-as-fast-primary-keys-under-multiple-database)

* [Do you really need a UUID/GUID?](https://rclayton.silvrback.com/do-you-really-need-a-uuid-guid)

* [Oracle Universal Unique Identifier](https://oracle-base.com/articles/9i/uuid-9i)

* [Oracle Reference 12c - SYS_GUID](https://docs.oracle.com/database/121/SQLRF/functions202.htm#SQLRF06120)

* [Why Auto Increment Is A Terrible Idea](https://www.clever-cloud.com/blog/engineering/2015/05/20/why-auto-increment-is-a-terrible-idea/)

* [Sequential UUID based entity key generator - COMB GUID](https://sites.google.com/site/rikkus/sequential-uuid-based-entity-key-generator-comb-guid)

* [UUID Collisions](https://softwareengineering.stackexchange.com/questions/130261/uuid-collisions)

* [Has there ever been a UUID collision?](https://www.quora.com/Has-there-ever-been-a-UUID-collision)

* [The article contains a footnote about UUIDs as primary keys](https://news.ycombinator.com/item?id=17135430)

* [Software engineer — from monolith to cloud: Auto Increment to UUID](https://coder.today/tech/2017-10-04_software-engineerfrom-monolith-to-cloud-auto-increment-to-uuid/)

* [When are you truly forced to use UUID as part of the design?](https://stackoverflow.com/questions/703035/when-are-you-truly-forced-to-use-uuid-as-part-of-the-design/786541)

* [How unique is UUID? - StackOverflow](https://stackoverflow.com/questions/1155008/how-unique-is-uuid)

* [When does it make sense in a RDBMS to make your primary key a UUID rather than an int?](https://www.quora.com/When-does-it-make-sense-in-a-RDBMS-to-make-your-primary-key-a-UUID-rather-than-an-int)

* [Why aren't programmers comfortable with the uniqueness guarantee of UUID version 4?](https://www.quora.com/Why-arent-programmers-comfortable-with-the-uniqueness-guarantee-of-UUID-version-4)

* [Is there any difference between a GUID and a UUID?](https://stackoverflow.com/questions/246930/is-there-any-difference-between-a-guid-and-a-uuid)

* [UUID1 vs UUID4](https://www.sohamkamani.com/blog/2016/10/05/uuid1-vs-uuid4/)

* [Probability of GUID collisions with different versions](https://news.ycombinator.com/item?id=10924343)

* [Are UUIDs really unique?](https://towardsdatascience.com/are-uuids-really-unique-57eb80fc2a87)

* [How good is Java's UUID.randomUUID?](https://stackoverflow.com/questions/2513573/how-good-is-javas-uuid-randomuuid)

* [ITU-T - ASN.1 PROJECT - Universally Unique Identifiers (UUIDs)](https://www.itu.int/en/ITU-T/asn1/Pages/UUID/uuids.aspx)

* [GUID/UUID Performance - MariaDB](https://mariadb.com/kb/en/library/guiduuid-performance/)

* [Difference between CLOCK_REALTIME and CLOCK_MONOTONIC? (StackOverflow)](https://stackoverflow.com/questions/3523442/difference-between-clock-realtime-and-clock-monotonic)

* [Is System.currentTimeMillis() monotonically increasing? (StackOverflow)](https://stackoverflow.com/questions/2978598/will-system-currenttimemillis-always-return-a-value-previous-calls)

* [GUID structure (Microsoft)](https://docs.microsoft.com/en-us/windows/win32/api/guiddef/ns-guiddef-guid)

* [UUID structure (Microsoft)](https://docs.microsoft.com/en-us/windows/win32/rpc/rpcdce/ns-rpcdce-uuid)

* [UuidCreate function (Microsoft)](https://docs.microsoft.com/pt-br/windows/win32/api/rpcdce/nf-rpcdce-uuidcreate)

* [UuidCreateSequential function (Microsoft)](https://docs.microsoft.com/pt-br/windows/win32/api/rpcdce/nf-rpcdce-uuidcreatesequential)

* [GUID/UUID Performance Breakthrough](http://mysql.rjweb.org/doc.php/uuid)

* [Storing UUID Values in MySQL Tables](http://mysqlserverteam.com/storing-uuid-values-in-mysql-tables/)

* [Going deep on UUIDs and ULIDs](https://www.honeybadger.io/blog/uuids-and-ulids/)

* [The mysterious “Ordered UUID”](https://itnext.io/laravel-the-mysterious-ordered-uuid-29e7500b4f8)

* [UUID proposal for ECMAScript](https://github.com/tc39/proposal-uuid/)

* [NPM UUID](https://www.npmjs.com/package/uuid)

* [How does the Docker assign MAC addresses to containers?](https://stackoverflow.com/questions/42946453/how-does-the-docker-assign-mac-addresses-to-containers/42947044#42947044)

* [EcmaScript UUID Proposal](https://github.com/tc39/proposal-uuid/issues/15#issuecomment-522415349)

* [User identifier (POSIX UID)](https://en.wikipedia.org/wiki/User_identifier)

* [Group identifier (POSIX GID)](https://en.wikipedia.org/wiki/Group_identifier)

* [Name spaces](https://en.wikipedia.org/wiki/Namespace)

* [Domain Name System](https://en.wikipedia.org/wiki/Domain_Name_System)

* [URL](https://en.wikipedia.org/wiki/URL);

* [ISO Object identifier](https://en.wikipedia.org/wiki/Object_identifier);

* [X.500](https://en.wikipedia.org/wiki/X.500);

