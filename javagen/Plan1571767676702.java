public class Plan1571767676702 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("B");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
