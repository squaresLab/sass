public class Plan1571770023343 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

StartServer("C");

}

for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

StartServer("A");

}

StartServer("C");


}
}
