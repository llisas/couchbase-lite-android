/**
 * Copyright (c) 2016 Couchbase, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.couchbase.lite;

import junit.framework.Assert;

public class SequenceMapTest extends LiteTestCase {
	
	public void testSequenceMap() {
		
		com.couchbase.lite.support.SequenceMap map = new com.couchbase.lite.support.SequenceMap();
		
		Assert.assertEquals(0, map.getCheckpointedSequence());
		Assert.assertEquals(null, map.getCheckpointedValue());
		Assert.assertTrue(map.isEmpty());
		
		Assert.assertEquals(1, map.addValue("one"));
		Assert.assertEquals(0, map.getCheckpointedSequence());
		Assert.assertEquals(null, map.getCheckpointedValue());
		Assert.assertTrue(!map.isEmpty());
		
		Assert.assertEquals(2, map.addValue("two"));
		Assert.assertEquals(0, map.getCheckpointedSequence());
		Assert.assertEquals(null, map.getCheckpointedValue());
		
		Assert.assertEquals(3, map.addValue("three"));
		Assert.assertEquals(0, map.getCheckpointedSequence());
		Assert.assertEquals(null, map.getCheckpointedValue());
		
		map.removeSequence(2);
		Assert.assertEquals(0, map.getCheckpointedSequence());
		Assert.assertEquals(null, map.getCheckpointedValue());
		
		map.removeSequence(1);
		Assert.assertEquals(2, map.getCheckpointedSequence());
		Assert.assertEquals("two", map.getCheckpointedValue());		
		
		Assert.assertEquals(4, map.addValue("four"));
		Assert.assertEquals(2, map.getCheckpointedSequence());
		Assert.assertEquals("two", map.getCheckpointedValue());
		
		map.removeSequence(3);
		Assert.assertEquals(3, map.getCheckpointedSequence());
		Assert.assertEquals("three", map.getCheckpointedValue());
		
		map.removeSequence(4);
		Assert.assertEquals(4, map.getCheckpointedSequence());
		Assert.assertEquals("four", map.getCheckpointedValue());
		Assert.assertTrue(map.isEmpty());
	}

}
