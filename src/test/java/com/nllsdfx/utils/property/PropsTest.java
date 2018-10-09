/*
 *     Copyright (C) 2018  Nllsdfx Utils
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.nllsdfx.utils.property;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Do not run this class. Use it's children for tests.
 */
@Ignore
public abstract class PropsTest {

    protected static Property property;
    protected CountDownLatch latch = new CountDownLatch(10);

    @Test
    public void set() {

        assert !property.set(null, null);
        assert !property.set("", null);
        assert !property.set("", "");
        assert !property.set(null, "");
        assert !property.set("", "");
        assert !property.set(" ", "        ");

        assert property.set("key", "value");

        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key1", "value1");

        assert property.set(map);

        map.put("fas", "fas");

        assert property.set(map);
    }

    @Test
    public void getAndSet() throws Exception {

        assert property.set("gg", "gg");
        assert property.get("gg").equals("gg");
        assert property.set("gg1", "gg");
        assert property.set("test", "true");

        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            service.execute(() -> {

                int rnd = ThreadLocalRandom.current().nextInt();

                String rndStr = Integer.toString(rnd);

                assert property.set(rndStr, rndStr);
                assert property.get(rndStr).equals(rndStr);

                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public abstract void isEmpty();
}
