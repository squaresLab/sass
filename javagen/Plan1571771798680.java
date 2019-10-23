public class Plan1571771798680 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");

}


DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}


for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("A");

}



}
}
