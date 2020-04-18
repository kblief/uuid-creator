
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

* __COMB GUID__: a GUID that combines random bits with the creation time (suffix);
* __Alternate COMB GUID__: a GUID that combines the creation time (prefix) with random bits.

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
  <version>2.0.1</version>
</dependency>
```
See more options in [maven.org](https://search.maven.org/artifact/com.github.f4b6a3/uuid-creator) and [mvnrepository.com](https://mvnrepository.com/artifact/com.github.f4b6a3/uuid-creator).

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

The DCE Security is a time-based UUID that also has a local domain and a local identifier.

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

### Version 4: Random-based

The Random UUID is a simple random array of 16 bytes. 

The default random generator is `SecureRandom`.

```java
// Random using the default SecureRandom generator
UUID uuid = UuidCreator.getRandomBased();
```

```java
// Random using the fast Xorshift128Plus generator
UUID uuid = UuidCreator.getFastRandomBased();
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

### Version 6: Time-ordered (proposed)

The Time-ordered UUID has a timestamp and may have a hardware address. 

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

### COMB GUID (non-standard)

The COMB GUID<sup>[7]</sup> is a modified random-based UUID that replaces the LAST 6 bytes with Unix epoch milliseconds.

The creation time is a SUFFIX after the random bytes.

```java
// COMB GUID
UUID uuid = UuidCreator.getCombGuid();
```

Examples of COMB GUID:

```text
19a298c8-3f03-0d00-57fe-0170df0cfcdd
01a8dee9-44dc-d928-419d-0170df0cfcdd
b7ba49dd-946c-b4a1-04dd-0170df0cfcdd
faa4a270-7223-e8d3-5ed4-0170df0cfcdd
cb019dcd-bc8e-1f84-f008-0170df0cfcdd
610cbbc9-d157-5882-752c-0170df0cfcdd
b77d6a98-3cf9-12fc-4a07-0170df0cfcdd
c26bf75c-f95d-bea1-7c4b-0170df0cfcdd
5655bf86-a00e-1b72-0081-0170df0cfcde < millisecond changed
459f1115-1ab1-3647-92f6-0170df0cfcde
ec2bad25-8e46-5b27-80d8-0170df0cfcde
95c1a524-574e-865e-f626-0170df0cfcde
1ec88587-8552-5eaa-72d5-0170df0cfcde
c72801dc-c3c5-d0b9-4335-0170df0cfcde
d790cb8d-1308-0672-b767-0170df0cfcde
6d885798-609b-9113-e879-0170df0cfcde
                                   ^ look

|----------------------|-----------|
       randomness        millisecs
```

### Alternate COMB GUID (non-standard)

The Alternate COMB GUID is a modified random-based UUID that replaces the FIRST 6 bytes with Unix epoch milliseconds.

The creation time is a PREFIX before the random bytes.

```java
// COMB GUID
UUID uuid = UuidCreator.getAltCombGuid();
```

Examples of COMB GUID:

```text
01716fdb-520c-744c-76c2-1281e82e3918
01716fdb-520c-f09f-7b17-11644106e621
01716fdb-520c-71ae-1c27-2204b5140680
01716fdb-520c-a2ab-930d-cf39a9259e40
01716fdb-520c-652f-595d-c9e268c5b28f
01716fdb-520c-bd53-f857-8d004d21bcbd
01716fdb-520c-e09f-6d2f-822731bbdf39
01716fdb-520c-f04a-2596-c05c38e0fb8b 
01716fdb-520d-70ac-71c2-7f8db1f67a87 < millisecond changed
01716fdb-520d-0d20-06a7-1e7041f28d19
01716fdb-520d-83c6-3e57-8b77409effb7
01716fdb-520d-fcd1-1b52-3e278610b940
01716fdb-520d-839e-b83a-e0014be989e2
01716fdb-520d-b5cc-9230-4505e0776e14
01716fdb-520d-f3af-1dd2-49401c60a242
01716fdb-520d-e0ae-36d1-4d80b5ae1fa0
            ^ look

|------------|---------------------|
   millisecs       randomness
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

The canonical format has 5 groups separated by dashes.

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
|1-------|2---||3--|

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
|1-------|2---||3--|

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


###  DCE Security

The DCE Security UUID inherits the same characteristics of the time-based UUID. The RFC-4122 doesn't describe the algorithm for generating this kind of UUID. These instructions are in the document "DCE 1.1: Authentication and Security Services".<sup>[6]</sup>

The difference is that it also contains information of local domain and local identifier. A half of the timestamp is replaced by a local identifier number. And half of the clock sequence is replaced by a local domain number.

### Name-based

There are two types of name-based UUIDs: MD5 and SHA-1. The MD5 is registered as version 3. And the SHA-1 is registered as version 5.

Two arguments are needed to generate a name-based UUID: a name space and a name.

The name space is an optional UUID argument.

The name argument may be a string or a byte array.

### Random

The random-based factory uses `java.security.SecureRandom`[<sup>&#x2197;</sup>](https://docs.oracle.com/javase/7/docs/api/java/security/SecureRandom.html) to get 'cryptographic quality random' numbers as the standard requires.

If the default `SecureRandom` is not desired, any instance of `java.util.Random` can be passed as parameter to the factory.

Fluent interface
------------------------------------------------------

The UUID factories are configurable via [fluent interface](https://en.wikipedia.org/wiki/Fluent_interface) methods. This section lists a lot of examples for each UUID version.


#### Time-based

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

#### Name-based

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

#### Random-based

```java

// with java random generator (java.util.Random)
UUID uuid = UuidCreator.getRandomBasedCreator()
    .withRandomGenerator(new Random())
    .create();

// with fast random generator (Xorshift128Plus)
UUID uuid = UuidCreator.getRandomBasedCreator()
    .withFastRandomGenerator()
    .create();

```

#### COMB GUID

All the examples in this subsection are also valid for Alternate COMB GUIDs.

```java

// with java random generator (java.util.Random)
UUID uuid = UuidCreator.getCombCreator()
    .withRandomGenerator(new Random())
    .create();

// with fast random generator (Xorshift128Plus)
UUID uuid = UuidCreator.getCombCreator()
    .withFastRandomGenerator()
    .create();

```

#### DCE Security

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
-------------------------------------------------------------------------------
THROUGHPUT (operations/millis)
-------------------------------------------------------------------------------
Benchmark                              Mode  Cnt      Score     Error   Units
-------------------------------------------------------------------------------
Throughput.EAIO_TimeBased (*)         thrpt    5  21301,487 ± 114,170  ops/ms
Throughput.Java_Random                thrpt    5   2233,614 ±  12,862  ops/ms
Throughput.UuidCreator_CombGuid       thrpt    5   2796,290 ±  11,834  ops/ms
Throughput.UuidCreator_FastRandom     thrpt    5  76560,183 ± 399,871  ops/ms
Throughput.UuidCreator_NameBasedMd5   thrpt    5   4068,016 ±   7,689  ops/ms
Throughput.UuidCreator_NameBasedSha1  thrpt    5   3012,655 ±   6,599  ops/ms
Throughput.UuidCreator_Random         thrpt    5   2223,155 ±   5,762  ops/ms
Throughput.UuidCreator_TimeBased      thrpt    5  18024,058 ±  73,906  ops/ms
Throughput.UuidCreator_TimeOrdered    thrpt    5  18135,001 ±  86,720  ops/ms
-------------------------------------------------------------------------------
Total time: 00:12:03
-------------------------------------------------------------------------------
```

These external generators are used for comparison:

- com.eaio.uuid.UUID (for time-based UUID);
- java.util.UUID (for random UUID).

Benchmarks executed in a PC with Ubuntu 20.04, JVM 11.0.7, CPU Intel i5-3330 and 8GB RAM.

You can find the benchmark source code at [uuid-creator-benchmark](https://github.com/fabiolimace/uuid-creator-benchmark).

(*) The UUID generated by EAIO is not an instance of `java.util.UUID`.

Related projects
------------------------------------------------------

* [commons](https://github.com/f4b6a3/commons): Reusable code

* [tsid-creator](https://github.com/f4b6a3/tsid-creator): Time Sortable ID generator

* [ulid-creator](https://github.com/f4b6a3/ulid-creator): Universally Unique Lexicographically Sortable ID generator

* [id-creator](https://github.com/f4b6a3/id-creator): Identifier generator that brings together the other generators

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

[1]: https://en.wikipedia.org/wiki/Universally_unique_identifier
[2]: https://tools.ietf.org/html/rfc4122
[3]: https://www.percona.com/blog/2007/03/13/to-uuid-or-not-to-uuid
[4]: https://www.percona.com/blog/2014/12/19/store-uuid-optimized-way
[5]: https://bradleypeabody.github.io/uuidv6
[6]: http://pubs.opengroup.org/onlinepubs/9696989899/chap5.htm#tagcjh_08_02_01_01
[7]: http://www.informit.com/articles/printerfriendly/25862
[8]: https://blogs.msdn.microsoft.com/dbrowne/2012/07/03/how-to-generate-sequential-guids-for-sql-server-in-net
[9]: https://github.com/ulid/spec

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

* [User identifier](https://en.wikipedia.org/wiki/User_identifier)

* [Group identifier](https://en.wikipedia.org/wiki/Group_identifier)
