public class Plan1571773833301 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");


DecreaseTraffic("A");

}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}


}
}
