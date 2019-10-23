public class Plan1571769943771 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

DecreaseDimmer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}





}
}
