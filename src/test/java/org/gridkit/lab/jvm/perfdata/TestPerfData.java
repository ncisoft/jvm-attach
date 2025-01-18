package org.gridkit.lab.jvm.perfdata;

import java.lang.management.ManagementFactory;

import org.gridkit.lab.jvm.perfdata.JStatData.StringCounter;
import org.junit.Assert;
import org.junit.Test;

public class TestPerfData {

    // sun.gc.collector.0.invocations: 26 Events [INTERNAL]
    // -- sun.gc.collector.1.invocations: 0 Events [INTERNAL]

    // sun.gc.collector.0.lastEntryTime: 29338473431694 Ticks [INTERNAL]
    // sun.gc.collector.0.lastExitTime: 29338477157895 Ticks [INTERNAL]

    // java.ci.totalTime: 50135412149 Ticks
    // sun.rt.applicationTime: 38818522555941 Ticks [INTERNAL]
    // sun.rt.createVmBeginTime: 1737079351650 None [INTERNAL]
    // sun.rt.createVmEndTime: 1737079351773 None [INTERNAL]
    private int pid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        name = name.substring(0, name.indexOf("@"));
        //return Integer.parseInt(name);
        return Integer.parseInt( "2842019");
    }

    @Test
    public void verify_self_attach() {
    	JStatData data = JStatData.connect(pid());
    	for(JStatData.Counter<?> c: data.getAllCounters().values()) {
    		Assert.assertNotNull(c.getName());
    		Assert.assertNotNull(c.getUnits());
    		Assert.assertNotNull(c.getVariability());
    		Assert.assertNotNull(c.getValue());
    		System.out.println(" -- " + c);
    		if (c instanceof StringCounter) {
    			String val = (String) c.getValue();
    			Assert.assertTrue(val.indexOf(0) < 0);
    		}
    	}
    }

    @Test
    public void verify_self_attach_reentrancy() {
    	JStatData data = JStatData.connect(pid());
    	for(JStatData.Counter<?> c: data.getAllCounters().values()) {
    		Assert.assertNotNull(c.getName());
    		Assert.assertNotNull(c.getUnits());
    		Assert.assertNotNull(c.getVariability());
    		Assert.assertNotNull(c.getValue());
    		if (c instanceof StringCounter) {
    			String val = (String) c.getValue();
    			Assert.assertTrue(val.indexOf(0) < 0);
    		}
    	}
    }
}
