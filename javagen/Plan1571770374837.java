public class Plan1571770374837 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("C");
StartServer("B");


}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");

}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("C");
StartServer("B");


}



}
}
