public class Plan1571772648841 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}

StartServer("C");

StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


}
}
