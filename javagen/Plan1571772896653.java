public class Plan1571772896653 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
DecreaseTraffic("A");
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
}



StartServer("B");

}


for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}


}
}
