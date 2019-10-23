public class Plan1571768112565 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");

DecreaseTraffic("A");

}

for (int i = 0; i < 5 ; i++) {
StartServer("C");
}



}
}
