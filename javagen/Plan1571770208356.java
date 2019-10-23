public class Plan1571770208356 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

DecreaseTraffic("A");

for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}



}
}
