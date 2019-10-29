public class Plan1571768274778 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
if ( IncreaseTraffic("C") ) {
IncreaseTraffic("B");
} else {
StartServer("A");
}

DecreaseTraffic("A");

StartServer("C");

StartServer("C");

}


}
}
