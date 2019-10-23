public class Plan1571773673460 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("A");

}

StartServer("B");
StartServer("B");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}



}
}
