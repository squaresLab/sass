public class Plan1571768515427 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
DecreaseTraffic("A");

}

for (int i = 0; i < 5 ; i++) {
StartServer("A");
StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
