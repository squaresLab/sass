public class Plan1525889263887 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("A");
StartServer("B");

StartServer("C");


}


}
}
