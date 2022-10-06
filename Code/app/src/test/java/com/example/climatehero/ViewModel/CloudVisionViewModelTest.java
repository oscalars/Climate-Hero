package com.example.climatehero.ViewModel;

import junit.framework.TestCase;

public class CloudVisionViewModelTest extends TestCase {

    public void testGetResult() {
        CloudVisionViewModel cloudVisionViewModel = new CloudVisionViewModel();
        assertTrue(cloudVisionViewModel.getResult() == null);
    }
}