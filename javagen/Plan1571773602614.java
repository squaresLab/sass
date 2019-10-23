public class Plan1571773602614 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}


}


}
}
