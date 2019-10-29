public class Plan1571771481834 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
IncreaseTraffic("B");
StartServer("C");


StartServer("A");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


}
}
