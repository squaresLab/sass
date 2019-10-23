public class Plan1571769936240 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


for (int i = 0; i < 4 ; i++) {
StartServer("B");
StartServer("A");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
