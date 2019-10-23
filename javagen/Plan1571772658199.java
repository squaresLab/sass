public class Plan1571772658199 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

}

for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}



}
}
