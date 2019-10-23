public class Plan1571774660929 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("C");
StartServer("B");


for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");
StartServer("A");


}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
}



}
}
