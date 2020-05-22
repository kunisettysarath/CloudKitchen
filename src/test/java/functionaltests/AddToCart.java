package functionaltests;

import org.testng.annotations.Test;

import generallibraries.BaseClass;
import generallibraries.ObjectRepository;

public class AddToCart extends BaseClass{

	@Test
	public void launchAndSelectMamacita() {
		initializeTestReporting("launchAndSelectMamacita");
		ObjectRepository.cloudKitchenHomeSteps.verifytitleAndSelectMamacita();
		ObjectRepository.cloudKitchenHomeSteps.verifyAddressPopUpAndInput("Seidengasse 44, 1070 Wien, Austria");
		ObjectRepository.cloudKitchenHomeSteps.addItemToCart("Chewy Chocolate Chip Cookie");
		
		
	}
}
