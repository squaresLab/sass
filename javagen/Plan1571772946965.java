public class Plan1571772946965 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("A");
}

IncreaseTraffic("C");

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}


}
}
