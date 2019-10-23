public class Plan1571771824648 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}



for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("C");
StartServer("A");

StartServer("A");


}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



DecreaseTraffic("A");

}
}
