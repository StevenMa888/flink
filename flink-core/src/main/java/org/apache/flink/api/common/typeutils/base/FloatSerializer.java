/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.api.common.typeutils.base;

import org.apache.flink.annotation.Internal;
import org.apache.flink.api.common.typeutils.SimpleTypeSerializerSnapshot;
import org.apache.flink.api.common.typeutils.TypeSerializerSnapshot;
import org.apache.flink.core.memory.DataInputView;
import org.apache.flink.core.memory.DataOutputView;

import java.io.IOException;

/**
 * Type serializer for {@code Float}.
 */
@Internal
public final class FloatSerializer extends TypeSerializerSingleton<Float> {

	private static final long serialVersionUID = 1L;

	/** Sharable instance of the FloatSerializer. */
	public static final FloatSerializer INSTANCE = new FloatSerializer();

	private static final Float ZERO = 0f;

	@Override
	public boolean isImmutableType() {
		return true;
	}

	@Override
	public Float createInstance() {
		return ZERO;
	}

	@Override
	public Float copy(Float from) {
		return from;
	}

	@Override
	public Float copy(Float from, Float reuse) {
		return from;
	}

	@Override
	public int getLength() {
		return 4;
	}

	@Override
	public void serialize(Float record, DataOutputView target) throws IOException {
		target.writeFloat(record);
	}

	@Override
	public Float deserialize(DataInputView source) throws IOException {
		return source.readFloat();
	}

	@Override
	public Float deserialize(Float reuse, DataInputView source) throws IOException {
		return deserialize(source);
	}

	@Override
	public void copy(DataInputView source, DataOutputView target) throws IOException {
		target.writeFloat(source.readFloat());
	}

	@Override
	public boolean canEqual(Object obj) {
		return obj instanceof FloatSerializer;
	}

	@Override
	public TypeSerializerSnapshot<Float> snapshotConfiguration() {
		return new FloatSerializerSnapshot();
	}

	// ------------------------------------------------------------------------

	/**
	 * Serializer configuration snapshot for compatibility and format evolution.
	 */
	@SuppressWarnings("WeakerAccess")
	public static final class FloatSerializerSnapshot extends SimpleTypeSerializerSnapshot<Float> {

		public FloatSerializerSnapshot() {
			super(() -> INSTANCE);
		}
	}
}
