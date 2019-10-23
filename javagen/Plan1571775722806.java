public class Plan1571775722806 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("C");


}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

StartServer("B");
StartServer("A");
DecreaseDimmer("A");





}
}
