public class Plan1571769804887 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("C");

}



}
}
