/**
 * Copyright 2018 Fabio Lima <br/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); <br/>
 * you may not use this file except in compliance with the License. <br/>
 * You may obtain a copy of the License at <br/>
 *
 * http://www.apache.org/licenses/LICENSE-2.0 <br/>
 *
 * Unless required by applicable law or agreed to in writing, software <br/>
 * distributed under the License is distributed on an "AS IS" BASIS, <br/>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. <br/>
 * See the License for the specific language governing permissions and <br/>
 * limitations under the License. <br/>
 *
 */

package com.github.f4b6a3.uuid.benchmark;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.NameBasedGenerator;
import com.fasterxml.uuid.impl.RandomBasedGenerator;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import com.github.f4b6a3.uuid.UUIDGenerator;
import com.github.f4b6a3.uuid.factory.DCESecurityUUIDCreator;
import com.github.f4b6a3.uuid.factory.OrderedUUIDCreator;
import com.github.f4b6a3.uuid.factory.TimeBasedUUIDCreator;

@State(Scope.Thread)
@Warmup(iterations = 1, batchSize = 1000)
@Measurement(iterations = 10, batchSize = 100000)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchmarkRunner {

	private String name;
	private byte[] bytes;

	private NameBasedGenerator jugNameBasedGenerator;
	private TimeBasedGenerator jugTimeBasedGenerator;
	private TimeBasedGenerator jugTimeBasedMACGenerator;
	private RandomBasedGenerator jugRandomGenerator;
	
	private OrderedUUIDCreator orderedUUIDCreator;
	private TimeBasedUUIDCreator timeBasedUUIDCreator;
	private OrderedUUIDCreator orderedMACUUIDCreator;
	private TimeBasedUUIDCreator timeBasedMACUUIDCreator;
	private DCESecurityUUIDCreator dceSecurityUUIDCreator;
	private DCESecurityUUIDCreator dceSecurityWithMACUUIDCreator;
	
	@Setup
	public void setUp() {
		
		name = "https://github.com/";
		bytes = name.getBytes();

		jugNameBasedGenerator = Generators.nameBasedGenerator();
		jugTimeBasedGenerator = Generators.timeBasedGenerator();
		jugTimeBasedMACGenerator = Generators.timeBasedGenerator(EthernetAddress.fromInterface());
		jugRandomGenerator = Generators.randomBasedGenerator();
		
		orderedUUIDCreator = UUIDGenerator.getOrderedCreator().withOverrunChecking(false);
		timeBasedUUIDCreator = UUIDGenerator.getTimeBasedCreator().withOverrunChecking(false);
		orderedMACUUIDCreator = UUIDGenerator.getOrderedCreator().withHardwareAddress().withOverrunChecking(false);
		timeBasedMACUUIDCreator = UUIDGenerator.getTimeBasedCreator().withHardwareAddress().withOverrunChecking(false);
		dceSecurityUUIDCreator = UUIDGenerator.getDCESecurityCreator().withOverrunChecking(false);
		dceSecurityWithMACUUIDCreator = UUIDGenerator.getDCESecurityCreator().withHardwareAddress().withOverrunChecking(false);
	}
	
	// Java UUID

	@Benchmark
	public UUID Java_Random() {
		return UUID.randomUUID();
	}

	@Benchmark
	public UUID Java_NameBased() {
		return UUID.nameUUIDFromBytes(bytes);
	}

	// EAIO

	@Benchmark
	public com.eaio.uuid.UUID EAIO_TimeBasedWithMAC() {
		return new com.eaio.uuid.UUID();
	}

	// JUG

	@Benchmark
	public UUID JUG_NameBased() {
		return jugNameBasedGenerator.generate(bytes);
	}

	@Benchmark
	public UUID JUG_TimeBased() {
		return jugTimeBasedGenerator.generate();
	}

	@Benchmark
	public UUID JUG_TimeBasedWithMAC() {
		return jugTimeBasedMACGenerator.generate();
	}

	@Benchmark
	public UUID JUG_Random() {
		return jugRandomGenerator.generate();
	}

	// UUID Generator

	@Benchmark
	public UUID UUIDGenerator_Random() {
		return UUIDGenerator.getRandom();
	}

	@Benchmark
	public UUID UUIDGenerator_FastRandom() {
		return UUIDGenerator.getFastRandom();
	}

	@Benchmark
	public UUID UUIDGenerator_DCESecurity() {
		return dceSecurityUUIDCreator.create((byte) 1, 1701);
	}
	
	@Benchmark
	public UUID UUIDGenerator_DCESecurityWithMAC() {
		return dceSecurityWithMACUUIDCreator.create((byte) 1, 1701);
	}

	@Benchmark
	public UUID UUIDGenerator_NameBasedMD5() {
		return UUIDGenerator.getNameBasedMD5(name);
	}

	@Benchmark
	public UUID UUIDGenerator_NameBasedSHA1() {
		return UUIDGenerator.getNameBasedSHA1(name);
	}

	@Benchmark
	public UUID UUIDGenerator_Ordered() {
		return orderedUUIDCreator.create();
	}

	@Benchmark
	public UUID UUIDGenerator_OrderedWithMAC() {
		return orderedMACUUIDCreator.create();
	}
	
	@Benchmark
	public UUID UUIDGenerator_TimeBased() {
		return timeBasedUUIDCreator.create();
	}

	@Benchmark
	public UUID UUIDGenerator_TimeBasedWithMAC() {
		return timeBasedMACUUIDCreator.create();
	}

	public static void main(String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}
}
