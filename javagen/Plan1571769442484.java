public class Plan1571769442484 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("B");
StartServer("A");


StartServer("C");

StartServer("C");


}

DecreaseDimmer("A");

}
}
