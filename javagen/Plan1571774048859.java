public class Plan1571774048859 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
StartServer("C");
StartServer("B");
StartServer("A");

StartServer("C");
DecreaseTraffic("A");




}


}
}
