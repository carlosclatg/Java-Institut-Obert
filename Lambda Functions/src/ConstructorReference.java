import java.util.ArrayList;
import java.util.List;

import java.util.function.Consumer;

public class ConstructorReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ZooFactory zooFactory = (List animals) -> {
			return new Zoo(animals);
		};
		ZooFactory zooFactory2 = Zoo::new;
		System.out.println("Ok");		
		Zoo zoo = zooFactory.getZoo(new ArrayList());
	}

}

interface ZooFactory {
    Zoo getZoo(List animals);
}
class Zoo {
    private List animalList;
    public Zoo(List animalList) {
        this.animalList = animalList;
		System.out.println("Zoo created.");
    }
}

