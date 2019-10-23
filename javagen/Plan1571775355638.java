public class Plan1571775355638 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("C");

}

StartServer("A");

StartServer("B");
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

StartServer("C");
StartServer("A");


StartServer("C");
DecreaseDimmer("A");


for (int i = 0; i < 3 ; i++) {
StartServer("B");
}




}
}
